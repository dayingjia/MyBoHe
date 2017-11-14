package com.mary.mybohe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mary.mybohe.util.StatusBarUtil;
import com.mary.mybohe.util.WindowUtil;

/**
 * Created on 17-11-13.
 */
public class MainViewGroup extends ViewGroup {
    private static final String TAG = "MainViewGroup";
    private Bitmap mainImage;
    private float startY;
    private int groupWidth;
    private Context context;

    public MainViewGroup(Context context) {
        this(context,null);
    }

    public MainViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MainViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setWillNotDraw(false);
        startY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,160,getResources().getDisplayMetrics());
        groupWidth = WindowUtil.getDefaultWidth(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: canvas.width="+canvas.getWidth()+"  canvas.height="+canvas.getHeight());
        drawMainImage(canvas);
    }

    private void drawMainImage(Canvas canvas) {
        if (mainImage == null) return;
        Paint paint = new Paint();
        canvas.drawBitmap(mainImage,0,0,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View view = getChildAt(0);
        measureChild(view,widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Log.i(TAG, "onLayout: height="+getChildAt(0).getMeasuredHeight());
        getChildAt(0).layout(i, (int) startY,i2, (int) (startY+getChildAt(0).getMeasuredHeight()));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }



    public void setMainImage(int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        //opt.inMutable
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),resId,opt);

        int bitmapWidth = opt.outWidth;
        int bitmapHeight = opt.outHeight;
        Log.i(TAG, "setMainImage: bitmapWidth="+bitmapWidth+"  bitmapHeight="+bitmapHeight);
        int screenWidth = WindowUtil.getDefaultWidth(context);
        int screenHeight = WindowUtil.getDefaultHeight(context)- StatusBarUtil.getStatusBarHeight(context);
        float scaleX = 1.0f;
        float scaleY = 1.0f;
        if (bitmapWidth>screenWidth||bitmapHeight>screenHeight){
            scaleX = bitmapWidth/screenWidth;
            scaleY = bitmapHeight/screenHeight;
        }else {
            scaleX = screenWidth/bitmapWidth;
            scaleY = screenHeight/bitmapHeight;
        }
        Log.i(TAG, "setMainImage: scaleX="+scaleX+" scaleY="+scaleY);
        //opt.inSampleSize = (int) (scaleX>scaleY?scaleX:scaleY);
        opt.inSampleSize = 2;
        opt.inJustDecodeBounds = false;
        Bitmap scaleBitmap = BitmapFactory.decodeResource(getResources(),resId,opt);

        /*Matrix matrix = new Matrix();
        float scaleX ;
        float scaleY ;
        if (bitmapWidth>screenWidth && bitmapHeight>screenHeight){
            scaleX = bitmapWidth/screenWidth;
            scaleY = bitmapHeight/screenHeight;
        }else {
            scaleX = screenWidth/bitmapWidth;
            scaleY = screenHeight/bitmapHeight;
        }

        matrix.postScale(scaleX,scaleY);
        Bitmap scaleBitmap = Bitmap.createBitmap(bitmap,0,0,screenWidth,screenHeight,matrix,true);*/
        if (bitmap!=null && !bitmap.equals(scaleBitmap)&& !bitmap.isRecycled()){
            bitmap.recycle();
            bitmap = null;
        }
        this.mainImage = scaleBitmap;
    }
}
