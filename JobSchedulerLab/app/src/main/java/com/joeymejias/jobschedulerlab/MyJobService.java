package com.joeymejias.jobschedulerlab;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by joey on 8/12/16.
 */
public class MyJobService extends JobService {

    private AsyncTask <Void, Void, String> mTask;

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        jobParameters.getExtras();

        mTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(System.currentTimeMillis());

                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

                String newText = "Time " + timeFormat.format(calendar.getTime());

                return newText;
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                DataSingleton.getInstance().updateMyText(string);
                jobFinished(jobParameters, false);
            }
        };
        mTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if(mTask.getStatus().equals(AsyncTask.Status.RUNNING)){
            mTask.cancel(false);
        }
        return false;
    }
}
