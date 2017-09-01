package kjy.com.mqtt.util;

import android.media.MediaRecorder;

import java.io.File;
import java.util.UUID;

/**
 * 录音管理工具类
 */
public class AudioManager {
    //AudioRecord: 主要是实现边录边播（AudioRecord+AudioTrack）以及对音频的实时处理。
    // 优点：可以语音实时处理，可以实现各种音频的封装
    private MediaRecorder mMediaRecorder;
    //录音文件
    private String mDir;
    //当前录音文件目录
    private String mCurrentFilePath;
    //单例模式
    private static AudioManager mInstance;
    //是否准备好
    private boolean isPrepare;

    //私有构造方法
    private AudioManager(String dir) {
        mDir = dir;
    }

    //对外公布获取实例的方法
    public static AudioManager getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }

    /**
     * 录音准备工作完成回调接口
     */
    public interface AudioStateListener {
        void wellPrepared();
    }

    public AudioStateListener mAudioStateListener;

    /**
     * 供外部类调用的设置回调方法
     * @param listener
     */
    public void setOnAudioStateListener(AudioStateListener listener) {
        mAudioStateListener = listener;
    }

    /**
     * 录音准备工作
     */
    public void prepareAudio() {
        try {
            isPrepare = false;
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdirs();//文件不存在，则创建文件
            }
            String fileName = generateFileName();
            File file = new File(dir, fileName);
            mCurrentFilePath = file.getAbsolutePath();
            mMediaRecorder = new MediaRecorder();
            // 设置输出文件路径
            mMediaRecorder.setOutputFile(file.getAbsolutePath());
            // 设置MediaRecorder的音频源为麦克风
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置音频格式为RAW_AMR
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            // 设置音频编码为AMR_NB
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 准备录音
            mMediaRecorder.prepare();
            // 开始，必需在prepare()后调用
            mMediaRecorder.start();
            // 准备完成
            isPrepare = true;
            if (mAudioStateListener != null) {
                mAudioStateListener.wellPrepared();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成录音文件名称
     * @return
     */
    private String generateFileName() {
        //随机生成不同的UUID
        return UUID.randomUUID().toString() + ".amr";
    }

    /**
     * 获取音量值
     * @param maxlevel
     * @return
     */
    public int getVoiceLevel(int maxlevel) {
        if (isPrepare) {
            try {
                // getMaxAmplitude返回的数值最大是32767
                return maxlevel * mMediaRecorder.getMaxAmplitude() / 32768 + 1;//返回结果1-7之间
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    /**
     * 释放资源
     */
    public void release() {
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        mMediaRecorder = null;
    }

    /**
     * 录音取消
     */
    public void cancel() {
        release();
        if (mCurrentFilePath != null) {
            //取消录音后删除对应文件
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }

    }

    /**
     * 获取当前文件路径
     * @return
     */
    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }
}
