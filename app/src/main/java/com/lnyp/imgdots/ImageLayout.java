package com.lnyp.imgdots;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;

    ImageView imgBg;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        LogUtils.e("w : " + w);
        LogUtils.e("h : " + h);

        addPoints(w, h);

    }

    /**
     * @param width  屏幕宽度
     * @param imgUrl 图片地址
     * @param scale  图片的宽高比
     */
    public void setImgBg(int width, String imgUrl, float scale) {

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = (int) (width * scale);

        imgBg.setLayoutParams(lp);

        Glide.with(getContext()).load(imgUrl).into(imgBg);

    }

    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    private void addPoints(int width, int height) {

        LogUtils.e("size : " + points.size());

        for (int i = 0; i < points.size(); i++) {

            float width_scale = points.get(i).width_scale;
            float height_scale = points.get(i).height_scale;

            ImageView imageView = new ImageView(getContext());
            imageView.setTag(i);
            imageView.setBackgroundResource(R.mipmap.point_img);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);

            imageView.setOnClickListener(this);

            layouPoints.addView(imageView, layoutParams);
        }
    }


    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        Toast.makeText(getContext(), "pos : " + pos, Toast.LENGTH_SHORT).show();
    }
}
