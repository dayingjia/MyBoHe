package com.mary.mybohe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mary.mybohe.R;

/**
 * Created on 17-11-10.
 */
public class HealthFoodAndSport extends View {
    private static final String TAG = "HealthFoodAndSport";

    private int viewWidth,viewHeight,barWidth;



    public HealthFoodAndSport(Context context) {
        this(context,null);
    }

    public HealthFoodAndSport(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HealthFoodAndSport(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "onMeasure: width="+widthSize+"  height="+heightSize);
        viewWidth = widthSize/2;
        viewHeight = heightSize;
        setMeasuredDimension(viewWidth,viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float startX = viewWidth/Item.values().length/2;
        float startY = viewHeight/8;
        float endY = viewHeight/8*4;
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#eeeeee"));
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        for (int i=0; i<Item.values().length;i++){
            canvas.drawLine(startX,startY,startX,endY,paint);
            startX += viewWidth/Item.values().length;
        }

    }

    void setNums(@IntRange(from = 0,to = 10)int... args){
        for (int i =0; i<Item.values().length;i++){
            Item.values()[i].setNum(args[i]);
        }
    }


    enum Item{
        breakfast(R.string.health_foodandsport_morning,0),
        lunch(R.string.health_foodandsport_noon,0),
        dinner(R.string.health_foodandsport_evening,0),
        add(R.string.health_foodandsport_add,0),
        sport(R.string.health_foodandsport_sport,0);
        int stringid;
        int num;
        Item(int stringid,int num){
            this.stringid = stringid;
            this.num = num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
