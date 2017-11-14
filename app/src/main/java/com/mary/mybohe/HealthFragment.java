package com.mary.mybohe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mary.mybohe.view.CommonContainer;
import com.mary.mybohe.view.HealthFoodAndSport;
import com.mary.mybohe.view.MainViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17-11-10.
 */
public class HealthFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "HealthFragment";
    private LinearLayout list1;
    private LinearLayout list2;
    private CommonContainer container;
    private TextView textView;
    private List<List> itemlist;
    private View view;
    private MainViewGroup mainViewGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_health,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.title).setVisibility(View.VISIBLE);
        itemlist = generateItemList();
        initView();
    }

    private void initView() {
        mainViewGroup = (MainViewGroup) view.findViewById(R.id.mainviewgroup);
        mainViewGroup.setMainImage(R.drawable.default_back);
        useList();
    }


    private void useList() {
        textView = new TextView(getContext());
        container = (CommonContainer) view.findViewById(R.id.container);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (List<Item> list:itemlist){
            list1 = new LinearLayout(getContext());
            //list1.setLayoutParams(lp);
            list1.setOrientation(LinearLayout.VERTICAL);
            for (Item item: list){
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_common,null);
                ((TextView)view.findViewById(R.id.item_main_title)).setText(item.title);
                if ( !item.subtitle.equals("")){
                    view.findViewById(R.id.item_sub).setVisibility(View.VISIBLE);
                    ((TextView)view.findViewById(R.id.item_sub_title)).setText(item.subtitle);
                    ((TextView)view.findViewById(R.id.item_sub_num)).setText(item.subtitlenum);

                    try {
                        ((LinearLayout)view.findViewById(R.id.view)).addView(item.view.getConstructor(Context.class).newInstance(getContext()));
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                view.setId(item.id);
                view.setOnClickListener(this);
                list1.addView(view);
            }
            container.addView(list1);
        }
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        int id = view.getId();
        Log.i(TAG, "onClick: id="+id);
        for (List<Item> list:itemlist){
            for(Item item:list){
                if (view.getId() == item.id){
                    intent = new Intent(getContext(),item.clazz);
                    startActivity(intent);
                }
            }
        }
    }


    class Item{
        private int id;
        private int resIcon;
        private String title;
        private String subtitle;
        private String subtitlenum;
        private Class<? extends View> view;
        private Class clazz;
        Item(int id,int resIcon, String title, String subtitle, String subtitlenum, Class<? extends Activity> clazz,Class<? extends View> view){
            this.id = id;
            this.resIcon = resIcon;
            this.title = title;
            this.subtitle = subtitle;
            this.subtitlenum = subtitlenum;
            this.view = view;
            this.clazz = clazz;
        }
    }

    List<List> generateItemList(){
        List list = new ArrayList();
        List<Item> list1 = new ArrayList<>();
        list1.add(new Item(R.id.item1,0,"item1","item1 sub","item1 num",SubActivity.class,HealthFoodAndSport.class));
        list1.add(new Item(R.id.item2,0,"item2","item2 sub","item2 num",SubActivity.class,HealthFoodAndSport.class));
        list1.add(new Item(R.id.item3,0,"item3","item3 sub","item3 num",SubActivity.class,HealthFoodAndSport.class));
        list1.add(new Item(R.id.item4,0,"item4","item4 sub","item4 num",SubActivity.class,HealthFoodAndSport.class));
        list.add(list1);
        return list;
    }
}
