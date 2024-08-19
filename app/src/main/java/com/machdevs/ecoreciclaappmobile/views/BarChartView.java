package com.machdevs.ecoreciclaappmobile.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.core.content.ContextCompat;

import com.machdevs.ecoreciclaappmobile.R;

public class BarChartView extends View {
    private float percentage;
    private Paint barPaint;
    private Paint backgroundPaint;
    private Paint textPaint;
    private float currentWidth;
    private int color;

    public BarChartView(Context context) {
        super(context);
        init();
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        barPaint = new Paint();
        backgroundPaint = new Paint();
        backgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.card_background_light));

        textPaint = new Paint();
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        textPaint.setTextSize(30f);
        textPaint.setTextAlign(Paint.Align.LEFT);
    }

    public void setPercentage(float percentage, int color) {
        this.percentage = percentage;
        this.color = color;
        barPaint.setColor(color);
        animateBar();
    }

    private void animateBar() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, percentage / 100f);
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentWidth = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Dibuja el fondo
        canvas.drawRect(0, 0, width, height, backgroundPaint);

        // Dibuja la barra
        float barWidth = Math.max(currentWidth * width, 2); // Asegura un mínimo de 2 píxeles
        canvas.drawRect(0, 0, barWidth, height, barPaint);

        // Dibuja el porcentaje
        String percentageText = String.format("%.1f%%", percentage);
        Rect bounds = new Rect();
        textPaint.getTextBounds(percentageText, 0, percentageText.length(), bounds);
        float textHeight = bounds.height();
        float textWidth = bounds.width();

        float textX = Math.min(barWidth + 5, width - textWidth - 5); // 5 píxeles de margen
        float textY = (height + textHeight) / 2;

        // Cambia el color del texto si está fuera de la barra
        if (textX > barWidth) {
            textPaint.setColor(ContextCompat.getColor(getContext(), R.color.text_primary_dark));
        } else {
            textPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        }

        canvas.drawText(percentageText, textX, textY, textPaint);
    }
}