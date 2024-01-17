package com.example.xml2;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TemperatureParser extends AsyncTask<Void, Void, List<String>> {

    private static final String TAG = TemperatureParser.class.getSimpleName();
    private static final String XML_URL = "https://www.aemet.es/xml/municipios/localidad_01059.xml";
    private static final String DIA = "dia";
    private static final String TEMPERATURA = "temperatura";
    private static final String TEMP_MINIMA = "minima";
    private static final String TEMP_MAXIMA = "maxima";

    private WeakReference<MainActivity> activityReference;

    public TemperatureParser(MainActivity activity) {
        this.activityReference = new WeakReference<>(activity);
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        List<String> temperatureList = new ArrayList<>();
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(XML_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            inputStream = connection.getInputStream();

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            int eventType = xmlPullParser.getEventType();
            String fecha = null;
            String minTemperature = null;
            String maxTemperature = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (name != null) {
                            if (name.equals("dia")) {
                                fecha = xmlPullParser.getAttributeValue(null, "fecha");
                            } else if (name.equals("maxima") && maxTemperature == null) {
                                eventType = xmlPullParser.next();
                                maxTemperature = xmlPullParser.getText();
                            } else if (name.equals("minima") && minTemperature == null) {
                                eventType = xmlPullParser.next();
                                minTemperature = xmlPullParser.getText();
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (name != null && name.equals("dia")) {
                            if (minTemperature != null && maxTemperature != null) {
                                temperatureList.add("Fecha: " + fecha + " - Mínima: " + minTemperature + " - Máxima: " + maxTemperature);
                                fecha = null;
                                minTemperature = null;
                                maxTemperature = null;
                            }
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }

        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, "Error: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing InputStream: " + e.getMessage());
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return temperatureList;
    }

    @Override
    protected void onPostExecute(List<String> temperatureList) {
        super.onPostExecute(temperatureList);
        MainActivity activity = activityReference.get();
        if (activity != null) {
            activity.setTemperatureList(temperatureList);
        }
    }
}
