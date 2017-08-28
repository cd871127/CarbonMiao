package anthony.apps.carbonmarket.net;

import anthony.apps.carbonmarket.config.ConfigParameter;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by chend on 2017/6/15.
 */
public class DefaultHttpClient {
    public static final CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);

        HttpHost proxy = new HttpHost("127.0.0.1", 1080, "http");
        RequestConfig requestConfig;

        if (ConfigParameter.proxy)
            requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setSocketTimeout(10000).setConnectTimeout(5000).build();
        else
            requestConfig = RequestConfig.custom()
                    .setSocketTimeout(10000).setConnectTimeout(5000).build();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler((exception, executionCount, context) -> {

                    if (executionCount > 3) {
                        System.out.println("Have retried 3 times, still time out, program exit");
                        System.exit(0);
                        return false;
                    }
                    System.out.println("Net work time out, auto retry");
                    return true;
                })
                .build();
    }
}
