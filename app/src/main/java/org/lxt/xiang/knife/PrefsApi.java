package org.lxt.xiang.knife;

import org.lxt.xiang.library.GET;
import org.lxt.xiang.library.PUT;

import java.util.Set;

public interface PrefsApi {


    @GET(key = "theme_type")
    String getTheme(String name);

    @PUT(key = "theme_type")
    void putType(String name);

    @GET
    int getId(int value);

    @PUT
    void setId(int id);

    @GET
    String getName(String name);

    @PUT
    void putName(String name);

    @GET
    long getAlbumId(long value);

    @PUT
    void setAlbumId(long id);

    @GET
    float getPercent(float value);

    @PUT
    void setPercent(float percent);

    @GET
    boolean getBool(boolean bool);

    @PUT
    void setBool(boolean bool);

    @GET
    Set<String> getSet(String apple, String pear);

    @PUT
    void setSet(String apple, String pear);

}
