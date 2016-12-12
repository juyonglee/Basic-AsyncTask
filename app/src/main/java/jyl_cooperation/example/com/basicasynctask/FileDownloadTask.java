package jyl_cooperation.example.com.basicasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by leejuyong on 2016. 12. 12..
 */

public class FileDownloadTask extends AsyncTask {

    private TextView downlaodStateTextView = null;
    public FileDownloadTask(TextView downloadStateTextView) {
        this.downlaodStateTextView = downloadStateTextView;
    }

    @Override
    protected void onPreExecute() {
        downlaodStateTextView.setText("File Downlaod....");
    }

    @Override
    protected Object doInBackground(Object[] downloadInfos) {
        for(int i=1; i<=downloadInfos.length; i++) {
            // 파일 내려받기 처리 상태를 표시하기 위해 호출
            publishProgress(i, downloadInfos.length);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        int currentCount = (int) values[0];
        int totalCount = (int) values[1];
        downlaodStateTextView.setText("Downloading: " + currentCount + "/" + totalCount);
    }

    @Override
    protected void onPostExecute(Object o) {
        if((boolean) o == true)
        {
            downlaodStateTextView.setText("Download Finish");
        } else {
            downlaodStateTextView.setText("Download Fail");
        }
    }

    @Override
    protected void onCancelled(Object o) {
    }

    @Override
    protected void onCancelled() {
        downlaodStateTextView.setText("DownLoad Cancle");
    }
}
