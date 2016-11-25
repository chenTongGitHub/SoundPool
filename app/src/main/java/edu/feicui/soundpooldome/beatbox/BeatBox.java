package edu.feicui.soundpooldome.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.feicui.soundpooldome.Sound;

/**
 * Created by Administrator on 2016/11/22.
 * 用于日志记录
 */

public class BeatBox {
    private static final String TAG="BeatBox";
    private static final String SOUNDS_FOLDER="sample_sounds";
    private static final int MAX_SOUNDS=6;
    //访问assets需要用到AssetManager类
    private AssetManager mAssets;
    private String[] soundNames;
    private List<Sound> mSounds=new ArrayList<>();
    private final SoundPool mSoundPool;

    public BeatBox(Context context){
        this.mAssets=context.getAssets();
        /**
         * SoundPool(int, int,int)
         * 1.第一个参数：指定同时播放多少个音频。
         * 此处指定了6个，在播放6个音频时，如果尝试播放第7个，则会停止播放原来的音频
         * 2.第二个参数：确定音频流类型。
         * Android有很多不同的音频流，都有各自独立的音量控制选项。如降低音乐音量，不会影响闹钟的音量
         * STREAM_MUSIC  使用的是同音乐和游戏一样的音量控制
         * 3.第三个参数：指定采样率转换品质。这个参数不起作用，一般传入0
         */
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);

        //调出声音文件的清单
        LoadSounds();
    }
    /**
     * 调出声音文件的清单的方法
     */
    private void LoadSounds(){
        String[] soundNames = new String[0];
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
        for (String filename:soundNames) {
            String assetPath=SOUNDS_FOLDER+"/"+filename;//拿到声音文件路径
            Sound sound=new Sound(assetPath);//拿到一个声音的对象
            try {
                load(sound);//将音频文件载入
            } catch (IOException e) {
                e.printStackTrace();
            }
            mSounds.add(sound);//将对象加入到集合中，初始化数据
        }
    }

    /**
     * 规范代码
     * @return
     */
    public List<Sound> getSounds(){
        return mSounds;
    }

    /**
     * 加载音频
     */
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = mAssets.openFd(sound.getmAssetPath());
        //调用mSoundPool.load(AssetFileDescriptor,int)可以把文件载入SoundPool待播
        int soundId = mSoundPool.load(assetFileDescriptor, 1);
        sound.setSoundId(soundId);
    }
    /**
     * 播放音频
     */
    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if (soundId==null){
            return;
        }
        //SoundPool.play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        //参数依次代表：音频Id、左音量、右音量、优先级（无效）、是否循环、播放速率
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
    }
    /**
     * 释放音频
     */
    public void release(){
         mSoundPool.release();
    }
}

