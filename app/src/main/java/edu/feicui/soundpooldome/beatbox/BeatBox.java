package edu.feicui.soundpooldome.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/22.
 * 用于日志记录
 */

public class BeatBox {
    private static final String TAG="BeatBox";
    private static final String SOUNDS_FOLDER="sample_sounds";
    //访问assets需要用到AssetManager类
    private AssetManager mAssets;
    private String[] soundNames;

    public BeatBox(Context context){
       this.mAssets=context.getAssets();
        //调出声音文件的清单
        LoadSounds();
    }
    /**
     * 调出声音文件的清单的方法
     */
    private void LoadSounds(){
        String[] soundNames;
        try {
            //拿到声音文件的文件名
            //AssetManager.list(String) 能列出指定目录中所有文件名
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.e(TAG, "Found  "+ soundNames.length+"个sound");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Could not list assets",e );
            // TODO: 2016/11/22  
        }
    }
}

