package com.example.jean.jcplayersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jean.jcplayer.JcAudio;
import com.example.jean.jcplayer.JcPlayerService;
import com.example.jean.jcplayer.JcPlayerView;
import com.example.jean.jcplayersample.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JcPlayerView.OnInvalidPathListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private JcPlayerView jcAudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jcAudioPlayer = (JcPlayerView) findViewById(R.id.jcplayer);

        ArrayList<JcAudio> jcAudios = new ArrayList<>();

        //      jcAudioPlayer.addAudio(JcAudio.createFromURL("url audio", "http://www.villopim.com.br/android/Music_01.mp3"));
//        jcAudioPlayer.addAudio(JcAudio.createFromAssets("49.v4.mid"));
//        jcAudioPlayer.addAudio(JcAudio.createFromRaw(R.raw.a_34));
//        jcAudioPlayer.addAudio(JcAudio.createFromFilePath(this.getFilesDir() + "/" + "121212.mmid"));

        List<String> strings = new ArrayList<>();
        strings.add("http://www.villopim.com.br/android/Music_01.mp3");
        strings.add("http://www.villopim.com.br/android/Music_02.mp3");
        strings.add("http://www.villopim.com.br/android/Music_03.mp3");
        strings.add("http://www.villopim.com.br/android/Music_04.mp3");
        strings.add("http://www.villopim.com.br/android/Music_05.mp3");
        strings.add("http://www.villopim.com.br/android/Music_06.mp3");
        for (String string : strings) {
            jcAudios.add(JcAudio.createFromURL("url audio",string));
        }

        jcAudioPlayer.initPlaylist(jcAudios);

        //  jcAudioPlayer.initWithTitlePlaylist(urls, "Awesome music");
        jcAudioPlayer.initPlaylist(jcAudios);
        jcAudioPlayer.registerInvalidPathListener(this);
        // jcAudioPlayer.registerStatusListener(this);

    }


    @Override
    public void onPause() {
        super.onPause();
        jcAudioPlayer.createNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jcAudioPlayer.kill();
    }


    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
    }


    @Override
    public void onPathError(JcAudio jcAudio) {
        Toast.makeText(this, jcAudio.getPath() + " with problems", Toast.LENGTH_LONG).show();
        jcAudioPlayer.removeAudio(jcAudio);
        jcAudioPlayer.next();

    }
}
