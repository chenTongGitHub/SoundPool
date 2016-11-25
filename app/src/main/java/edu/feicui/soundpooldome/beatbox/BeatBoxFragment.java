package edu.feicui.soundpooldome.beatbox;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.feicui.soundpooldome.R;
import edu.feicui.soundpooldome.Sound;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeatBoxFragment extends Fragment  {


    @BindView(R.id.fragment_beat_box_recycler_view)
    RecyclerView recyclerView;
    private BeatBox beatBox;//日志记录

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        ButterKnife.bind(this, view);
        //设置布局管理器，设置为3列
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //绑定适配器
        SoundAdapter adapter=new SoundAdapter(beatBox.getSounds());
        recyclerView.setAdapter(adapter);
        //adapter.setOnItemClickListener(this);
        return view;
    }

//    @Override
//    public void OnItemClick(View view, int position) {
//
//    }

    /**
     * 创建适配器
     * 1.创建viewHolder
     * 2.创建Adapter
     */
    public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {
        private List<Sound> mSounds;
        private SoundHolder soundHolder;

        //构造方法传参
        public SoundAdapter(List<Sound> mSounds) {
            if (mSounds==null){
                mSounds=new ArrayList<>();
            }
            this.mSounds = mSounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            soundHolder = new SoundHolder(inflater,parent);
            return soundHolder;
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            soundHolder=holder;
            Sound sound=mSounds.get(position);
            ViewGroup.LayoutParams params=soundHolder.soundButton.getLayoutParams();
            params.height= (int) (Math.random()*100+200);

            soundHolder.soundButton.setLayoutParams(params);
            soundHolder.bindSound(sound);
            //soundHolder.itemView.setOnClickListener(new myOnClickListener(position));
        }

        @Override
        public int getItemCount() {
            return mSounds.size() == 0 ? 0 : mSounds.size();
        }

        /**
         * 创建viewHolder
         */
        public class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private Button soundButton;
            private Sound mSound;

            public SoundHolder(LayoutInflater inflater, ViewGroup container) {
                super(inflater.inflate(R.layout.list_item_sound, container, false));
                soundButton= (Button) itemView.findViewById(R.id.list_item_sound_button);
                soundButton.setOnClickListener(this);
            }

            /**
             * 渲染控件的方法，便于适配器中调用
             * @param mSound 传入的音频文件
             */
            public void bindSound(Sound mSound){
                this.mSound=mSound;
                soundButton.setText(mSound.getmName());
            }

            @Override
            public void onClick(View view) {
                beatBox.play(mSound);
            }
        }


//    public class myOnClickListener implements View.OnClickListener{
//        private int position;
//        public myOnClickListener(int position){
//            this.position=position;
//        }
//        @Override
//        public void onClick(View view) {
//
//            mOnItemClickListener.onItemClick(view,position);
//        }
//    }
    //***********写回调接口，用于子条目点击********
//    public interface OnItemClickListener{
//        void OnItemClick(View view,int position);
//    }
//    OnItemClickListener mOnItemClickListener;
//    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
//        this.mOnItemClickListener=mOnItemClickListener;
//    }
}

    /**
     * 记得要释放SoundPool资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        beatBox.release();
    }
}
