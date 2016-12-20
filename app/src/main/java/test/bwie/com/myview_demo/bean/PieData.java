package test.bwie.com.myview_demo.bean;

/**
 * Author：ZhaoTieJin
 * date :  2016/12/20
 */

public class PieData {
    //这里的属性是自己定义的，并非每一个都是必须的，
    // 只要保证能够给public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
    //这个方法提供所需的一些参数即可，如：起始角度、扫过的角度...
    private String name;//名字
    private int color;//颜色--该扇形颜色
    private float angle;//角度--该扇形角度

    public PieData(String name, int color, float angle) {
        this.name = name;
        this.color = color;
        this.angle = angle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
