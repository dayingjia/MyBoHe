package com.mary.mybohe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.mary.mybohe.util.WindowUtil;

/**
 * Created on 17-11-9.
 */
public class CommonContainer extends ViewGroup {
    private static final String TAG = "CommonContainer";
    private Context context;

    private int margin;

    public CommonContainer(Context context) {
        this(context,null);
    }

    public CommonContainer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics());
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "onMeasure: widthSize="+widthSize+"  heightSize="+heightSize);
        int height = 0;
        Log.i(TAG, "onMeasure: getChildCount="+getChildCount());
        for (int i=0 ; i< getChildCount();i++){
            Log.i(TAG, "onMeasure: WindowUtil.getDefaultWidth(context)="+WindowUtil.getDefaultWidth(context));
            View view = getChildAt(i);
            Log.i(TAG, "onMeasure: this is item_common i="+i);
            /*if (view instanceof LinearLayout){
                LinearLayout itemll = (LinearLayout)view;
                for(int j=0; j< itemll.getChildCount(); j++){
                    Log.i(TAG, "onMeasure: this is main or sub linearlayout j="+j);
                    if (itemll.getChildAt(j) instanceof LinearLayout){
                        LinearLayout itemsubll = (LinearLayout) itemll.getChildAt(j);
                        for (int k=0; k<itemsubll.getChildCount(); k++){
                            Log.i(TAG, "onMeasure: this is ll in main or sub k="+k);
                            Log.i(TAG, "onMeasure: itemview="+itemsubll.getChildAt(k).getMeasuredHeight());
                            if (itemsubll.getChildAt(k) instanceof HealthFoodAndSport){
                                Log.i(TAG, "onMeasure: itemview="+itemsubll.getChildAt(k).getMeasuredHeight());
                            }
                            if (itemsubll.getChildAt(k) instanceof LinearLayout){

                                LinearLayout itemViewll = (LinearLayout) itemsubll.getChildAt(k);
                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) itemViewll.getLayoutParams();
                                Log.i(TAG, "onMeasure: lp.width="+lp.width);
                                for (int n=0; n<itemViewll.getChildCount();n++){
                                    //Log.i(TAG, "onMeasure: view="+itemViewll.getChildAt(n).getMeasuredHeight());
                                    if (itemViewll.getChildAt(n).getId() == R.id.view){
                                        itemViewll.getChildAt(n).measure(MeasureSpec.makeMeasureSpec(lp.width,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(lp.height,MeasureSpec.EXACTLY));
                                        Log.i(TAG, "onMeasure: itemview="+itemViewll.getChildAt(n).getMeasuredHeight());
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
            int widthMS = MeasureSpec.makeMeasureSpec(WindowUtil.getDefaultWidth(context),MeasureSpec.EXACTLY);
            LayoutParams lp = view.getLayoutParams();
            Log.i(TAG, "onMeasure: lp.width="+lp.width);
            view.measure(widthMS,resolveSize(lp.height,heightMeasureSpec));
            Log.i(TAG, "onMeasure: view.width="+view.getMeasuredWidth());
            height += view.getMeasuredHeight();
            Log.i(TAG, "onMeasure: view.getMeasuredHeight()="+view.getMeasuredHeight());
            height += margin;
        }
        Log.i(TAG, "onMeasure: height="+height);
        Log.i(TAG, "onMeasure: resolveSize="+resolveSize(height,heightMeasureSpec));
        setMeasuredDimension(resolveSize(WindowUtil.getDefaultWidth(context),widthMeasureSpec),resolveSize(height,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        int start = 0;
        Log.i(TAG, "onLayout: top="+top);
        for (int i = 0; i< getChildCount(); i++){
            View view = getChildAt(i);
            view.layout(left,start,right,start+view.getMeasuredHeight());
            Log.i(TAG, "onLayout: start="+start);
            start += margin+view.getMeasuredHeight();
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }
}
