package com.example.xml1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String RSS_FEED_URL = "https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada";

    private ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = findViewById(R.id.newsListView);

        LoadRssTask loadRssTask = new LoadRssTask() {
            @Override
            protected void onPostExecute(List<NewsItem> newsList) {
                ArrayAdapter<NewsItem> adapter = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1, newsList) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView textView = view.findViewById(android.R.id.text1);
                        textView.setText(newsList.get(position).getTitle());
                        return view;
                    }
                };
                newsListView.setAdapter(adapter);
            }
        };

        loadRssTask.execute(RSS_FEED_URL);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                NewsItem selectedNews = (NewsItem) adapterView.getItemAtPosition(position);
                String link = selectedNews.getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            }
        });
    }
}