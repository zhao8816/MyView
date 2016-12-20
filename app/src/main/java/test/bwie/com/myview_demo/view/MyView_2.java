package test.bwie.com.myview_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import test.bwie.com.myview_demo.bean.PieData;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/20
 */

/**
 * 画几个扇形，扇形参数自定义，由activity传值。
 */
public class MyView_2 extends View {

    private RectF rectF;
    private Paint paint_1;
    private Paint paint_2;
    private int mWidth;
    private int mHeight;
    private int startAngle;//起始角度
    private ArrayList<PieData> piedata_list;

    public MyView_2(Context context) {
        this(context, null);
    }

    public MyView_2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化工具
        initTools();
    }

    //初始化工具
    private void initTools() {
        //矩形
        rectF = new RectF(100, 100, 500, 500);
        paint_1 = new Paint();//实例化两个画笔
        paint_2 = new Paint();
        paint_2.setColor(Color.GRAY);//画笔颜色
        paint_2.setStrokeWidth(20);//画笔宽度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画布原点的位置
        canvas.translate(mWidth / 3, mHeight / 3);//画布原点位置移动到屏幕中心X、Y轴的1/3处
        //设置画布背景
        canvas.drawColor(Color.YELLOW);
        //画出矩形
        canvas.drawRect(rectF, paint_2);
        //循环绘制扇形
        for (int i = 0; i < piedata_list.size(); i++) {
            //设置画笔颜色
            paint_1.setColor(piedata_list.get(i).getColor());
            //绘制扇形
            canvas.drawArc(rectF, startAngle, piedata_list.get(i).getAngle(), true, paint_1);
            //每循环一次，起始角度增加前一个扇形的角度
            startAngle += piedata_list.get(i).getAngle();
            //设置字体大小
            paint_2.setTextSize(50f);
            //设置显示内容
            canvas.drawText("扇形图", 350, 600, paint_2);
        }
    }

    //重写onSizeChanged()方法，画布尺寸改变时，才会调用
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //把屏幕的宽高赋给这两个变量，当画布尺寸改变的时候会用到
        mWidth = w;
        mHeight = h;
    }

    //创建一个供外部传参的方法
    public void setPieData(ArrayList<PieData> list) {
        this.piedata_list = list;
    }
}
