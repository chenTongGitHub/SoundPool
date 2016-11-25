package edu.feicui.soundpooldome;

/**
 * Created by Administrator on 2016/11/22.
 * Sound管理类，用来管理声音，将其展示给用户（Sound对象）
 */

public class Sound {
    private String mAssetPath;//声音文件路径
    private String mName;
    /**
     * SoundPool加载音频的优势：指令刚一发出，立即播放。
     * 前提：必须预先加载音频。SoundPool加载音频文件有自己的Integer类型ID
     */
    private Integer mSoundId;

    public Sound(String mAssetPath){
        this.mAssetPath=mAssetPath;
        /**
         * 拿到文件名
         * 1.用"/"分隔，得到数组
         * 2.文件名.文件类型是数组最后一位
         * 3.将“.文件类型”去掉就是文件名
         */
        String[] components = mAssetPath.split("/");
        String fileName = components[components.length - 1];
        mName=fileName.replace(".wav","");
    }

    public String getmAssetPath() {
        return mAssetPath;
    }

    public String getmName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
