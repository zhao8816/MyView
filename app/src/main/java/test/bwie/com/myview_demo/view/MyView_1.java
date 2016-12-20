package test.bwie.com.myview_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/20
 */

/**
 * 自定义view，画一个圆，并添加触摸监听
 */
public class MyView_1 extends View {

    private Paint paint;
    private float cx = 150, cy = 150, radius = 100;
    private float distance_1;
    private float distance_2;

    //定义枚举类，标记手指状态，意思就是一次性定义多个同类的变量
    private enum Mode {
        NONE, DRAG, ZOOM
    } //无，拖拽，缩放

    private Mode mode = Mode.NONE;//触摸事件的模式，默认为none，无动作

    //自定义view，要重写三个构造方法，并建立关联
    public MyView_1(Context context) {
        this(context, null);
    }

    public MyView_1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化工具
        initTools();
    }

    //初始化工具
    private void initTools() {

        paint = new Paint();//实例化画笔
        paint.setColor(Color.GREEN);//画笔设置颜色
        paint.setAntiAlias(true);//画笔平滑、武锯齿
        paint.setStyle(Paint.Style.STROKE);//画笔风格，FILL_AND_STROKE-实心有边，FILL-实心无边；STROKE-空心。
        paint.setStrokeWidth(50);//画笔线宽
    }

    //重写onDraw方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
          /*  画一个圆：
            canvas.drawCircle(150,150,100,paint);
            参数解释：左边距、上边距、半径、画笔
         */

        canvas.drawColor(Color.GRAY);//画布颜色
//        canvas.drawCircle(150,150,100,paint);//画圆

        //动态画圆
        canvas.drawCircle(cx, cy, radius, paint);
    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = Mode.DRAG;//单指按下，拖拽模式
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode = Mode.ZOOM;//双指按下，缩放模式
                //记录两指间距
                distance_1 = getDistance(event);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = Mode.NONE;//双指抬起，无动作
                break;
            case MotionEvent.ACTION_UP:
                mode = Mode.NONE;//单指抬起，无动作
                break;
            case MotionEvent.ACTION_MOVE://滑动的时候先做判断
                //拖拽模式
                if (mode == Mode.DRAG) {
                    //将触摸点的坐标，作为新圆距离左、上的距离，重新画，就形成了移动
                    cx = event.getRawX();
                    cy = event.getRawY();
                }


                //缩放模式
                if (mode == Mode.ZOOM) {
                    //获取移动后两指间的距离
                    distance_2 = getDistance(event);
                    float gapDis = (distance_2 - distance_1) / 4;//为了变化不那么突兀
                    //重置半径
                    radius += gapDis;
                    //设置半径最大值、最小值。不至于无限放大无限缩小
                    if (radius > 300f) {
                        radius = 300f;
                    }
                    if (radius < 50f) {
                        radius = 50f;
                    }
                }
                break;
        }
        invalidate();//重绘
        return true;
    }

    //自定义一个计算两点之间距离的方法
    private float getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }
}
