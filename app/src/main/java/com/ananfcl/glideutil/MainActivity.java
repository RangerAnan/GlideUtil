package com.ananfcl.glideutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ananfcl.glideutil.glide.GlideUtils;

/**
 * 测试Glide
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ImageView mIvThumb;
    private ImageView iv_centerCrop;
    private ImageView iv_fitCenter;
    private ImageView iv_gif, iv_staticGif, iv_radius, iv_blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mIvThumb = (ImageView) findViewById(R.id.iv_thumb);
        iv_centerCrop = (ImageView) findViewById(R.id.iv_centerCrop);
        iv_fitCenter = (ImageView) findViewById(R.id.iv_fitCenter);
        iv_gif = (ImageView) findViewById(R.id.iv_gif);
        iv_staticGif = (ImageView) findViewById(R.id.iv_staticGif);
        iv_radius = (ImageView) findViewById(R.id.iv_radius);
        iv_blur = (ImageView) findViewById(R.id.iv_blur);

        initRecycler();

        //2.显示缩略图
        GlideUtils.loadImageViewThumbnail(this, Images.imageThumbUrls[10], mIvThumb);
        GlideUtils.loadImageViewCrop(this, Images.imageThumbUrls[10], iv_centerCrop);
        GlideUtils.loadImageViewFit(this, Images.imageThumbUrls[10], iv_fitCenter);
        GlideUtils.loadImageViewDynamicGif(this, Images.imageGifPath, iv_gif);
        GlideUtils.loadImageViewStaticGif(this, Images.imageGifPath, iv_staticGif);
        GlideUtils.loadCircleImage(this, Images.imageThumbUrls[10], iv_radius);
        GlideUtils.loadBlurImage(this, Images.imageThumbUrls[10], iv_blur);

    }

    private void initRecycler() {
        linearLayoutManager = new LinearLayoutManager(this);

//        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        mRecyclerView.setAdapter(new MyAdapter(Images.imageThumbUrls));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        String[] imageThumbUrls;

        public MyAdapter(String[] imageThumbUrls) {
            this.imageThumbUrls = imageThumbUrls;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            GlideUtils.loadImageView(MainActivity.this, imageThumbUrls[position], holder.item_iv,
                    R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return imageThumbUrls.length;
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView item_iv;

            public ViewHolder(View view) {
                super(view);
                item_iv = (ImageView) view.findViewById(R.id.item_iv);
            }
        }
    }
}
