package jyl_cooperation.example.com.basicasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView downloadStateTextView = null;
    FileDownloadTask fileDownloadTask = null;
    Button fileDownloadButton = null;
    String[] downloadFile = {"FileUrl1", "FileUrl2", "FileUrl3", "FileUrl4", "FileUrl5", "FileUrl6", "FileUrl7", "FileUrl8", "FileUrl9", "FileUrl10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadStateTextView = (TextView) findViewById(R.id.download_state_textview);
        fileDownloadButton = (Button) findViewById(R.id.download_action_button);

        fileDownloadTask = new FileDownloadTask(downloadStateTextView, fileDownloadButton);

        fileDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileDownloadTask != null && fileDownloadTask.getStatus() == AsyncTask.Status.PENDING) {
                    fileDownloadTask.execute(downloadFile);
                }
                else if(fileDownloadTask.getStatus() == AsyncTask.Status.RUNNING) {
                    fileDownloadTask.cancel(true);
                }
                else if(fileDownloadTask.getStatus() == AsyncTask.Status.FINISHED) {
                    if(fileDownloadTask != null) {
                        fileDownloadTask = new FileDownloadTask(downloadStateTextView, fileDownloadButton);
                        fileDownloadTask.execute(downloadFile);
                    }
                }
            }
        });
    }
}
