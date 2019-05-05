package com.alian.application.avatarapp.features.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.alian.application.avatarapp.R;


public class CircularProgress extends View {

    private float strokeWidth = getResources().getDimension(R.dimen.circular_border_width);
    private float progress = 0;
    private int min = 0;
    private int max = 100;
    private int color = getResources().getColor(R.color.ocean_blue);
    private int bgColor = getResources().getColor(R.color.white);
    private RectF rectF;
    private Paint backgroundPaint;
    private Paint foregroundPaint;

    public CircularProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rectF = new RectF();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircularProgress,
                0, 0);

        try {
            strokeWidth = typedArray.getDimension(R.styleable.CircularProgress_progressBarThickness, strokeWidth);
            progress = typedArray.getFloat(R.styleable.CircularProgress_progress, progress);
            color = typedArray.getInt(R.styleable.CircularProgress_progressbarColor, color);
            bgColor = typedArray.getInt(R.styleable.CircularProgress_progressbarBGColor, bgColor);
            min = typedArray.getInt(R.styleable.CircularProgress_min, min);
            max = typedArray.getInt(R.styleable.CircularProgress_max, max);
        } finally {
            typedArray.recycle();
        }

        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(adjustAlpha(bgColor));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(strokeWidth);

        foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        foregroundPaint.setColor(color);
        foregroundPaint.setStyle(Paint.Style.STROKE);
        foregroundPaint.setStrokeWidth(strokeWidth);
    }

    private int adjustAlpha(int color) {
        int alpha = Math.round(Color.alpha(color) * (float) 0.3);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        rectF.set(0 + strokeWidth / 2, 0 + strokeWidth / 2, min - strokeWidth / 2, min - strokeWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawOval(rectF, backgroundPaint);
        float angle = 360 * progress / max;
        int startAngle = -90;
        canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint);

    }

    public void setProgress(float progress) {
        if (progress == 0 || progress == 100) {
            color = getResources().getColor(R.color.white);
        } else {
            this.progress = progress;
        }
        invalidate();
    }
}
