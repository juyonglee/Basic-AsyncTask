package jyl_cooperation.example.com.basicasynctask;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by leejuyong on 2016. 12. 12..
 */

public class FileDownloadTask extends AsyncTask<String, Integer, Boolean> {

    private TextView downlaodStateTextView = null;
    private Button downloadButton = null;

    public FileDownloadTask(TextView downloadStateTextView, Button downloadButton) {
        this.downlaodStateTextView = downloadStateTextView;
        this.downloadButton = downloadButton;
    }

    @Override
    protected void onPreExecute() {
        downlaodStateTextView.setText("File Downlaod....");
        downloadButton.setText(R.string.download_button_state_cancle);
    }

    @Override
    protected Boolean doInBackground(String... downloadInfos) {
        for(int i=1; i<=downloadInfos.length; i++) {
            // 파일 내려받기 처리 상태를 표시하기 위해 호출
            publishProgress(i, downloadInfos.length);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int currentCount = values[0];
        int totalCount = values[1];
        downlaodStateTextView.setText("Downloading: " + currentCount + "/" + totalCount);
    }

    @Override
    protected void onPostExecute(Boolean o) {
        if((boolean) o == true)
        {
            downlaodStateTextView.setText("Download Finish");
        } else {
            downlaodStateTextView.setText("Download Fail");
        }
        downloadButton.setText(R.string.downlaod_button_state_start);
    }

    @Override
    protected void onCancelled() {
        downlaodStateTextView.setText("DownLoad Cancle");
        downloadButton.setText(R.string.downlaod_button_state_start);
    }
}
