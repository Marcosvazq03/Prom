package com.example.xml1;

import android.os.AsyncTask;
import android.util.Log;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadRssTask extends AsyncTask<String, Void, List<NewsItem>> {

    private static final String TAG = LoadRssTask.class.getSimpleName();

    @Override
    protected List<NewsItem> doInBackground(String... urls) {
        List<NewsItem> newsList = new ArrayList<>();

        try {
            URL feedUrl = new URL(urls[0]);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            for (SyndEntry entry : feed.getEntries()) {
                String title = entry.getTitle();
                String link = entry.getLink();
                newsList.add(new NewsItem(title, link));
            }
        } catch (IOException | FeedException e) {
            Log.e(TAG, "Error loading RSS feed", e);
        }

        return newsList;
    }
}
