package com.zhangwx.dynamicpermissionsrequest;

import android.app.Service;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    AudioManager mAudioManager;
    AudioTrack at;
    AudioRecord audioRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAudioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        final int bufferSizeInBytes = AudioTrack.getMinBufferSize(
                44100,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        at = new AudioTrack(
                AudioManager.STREAM_VOICE_CALL,
                44100,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSizeInBytes,
                AudioTrack.MODE_STREAM);

        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String sPhoneNo = tel.getLine1Number();

        initOthers();

    }

    private void initOthers() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
                setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            }
        });
    }
}
