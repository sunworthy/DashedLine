package com.dashline.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.dashline.R;

/**
 * Created by king on 2016/9/19.
 * 参考：http://tech2ipo.com/38255
 * http://blog.csdn.net/xushuaic/article/details/38975915
 */
public class DashedLine extends View {
    private boolean isVertical = false;//draw vertical or horizontal line
    private int lineColor = Color.WHITE;
    public DashedLine(Context context) {
        this(context,null);
    }
    public DashedLine(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public DashedLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DashedLine);
        isVertical = a.getBoolean(R.styleable.DashedLine_isVertical,false);
        lineColor =  a.getColor(R.styleable.DashedLine_dashedLineColor,Color.WHITE);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(px2dp(2));
        paint.setColor(lineColor);
        Path path = new Path();
        int w=getWidth();
        int h=getHeight();
        if(isVertical){
            path.moveTo(w/2, 0);
            path.lineTo(w/2, h);
        }else{
            path.moveTo(0, h/2);
            path.lineTo(w, h/2);
        }
        int lineG=px2dp(4);
        PathEffect effects = new DashPathEffect(new float[]{lineG,lineG,lineG,lineG},px2dp(2));
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
    private int px2dp(int px){
        return (int) (px * getResources().getDisplayMetrics().density + 0.5f);
    }
}