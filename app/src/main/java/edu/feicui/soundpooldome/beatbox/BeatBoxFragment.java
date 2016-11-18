package edu.feicui.soundpooldome.beatbox;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.feicui.soundpooldome.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeatBoxFragment extends Fragment {


    @BindView(R.id.fragment_beat_box_recycler_view)
    RecyclerView recyclerView;

    public BeatBoxFragment() {
        // Required empty public constructor
    }

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        ButterKnife.bind(this, view);
        //设置布局管理器，设置为3列
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //绑定适配器
        recyclerView.setAdapter(new SoundAdapter());
        return view;
    }

    /**
     * 创建适配器
     * 1.创建viewHolder
     * 2.创建Adapter
     */
    private class SoundHolder extends RecyclerView.ViewHolder {
        public SoundHolder(View itemView) {
            super(itemView);
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {

        @BindView(R.id.list_item_sound_button)
        Button soundButton;

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_sound, parent, false);
            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


}
