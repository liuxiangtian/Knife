

一个 SharedPreferences 工具类，注解实现，使用方法

**1.定义接口类**
```
public interface PrefsApi {

    @GET(key = "theme_type")
    String getTheme(String name);

    @PUT(key = "theme_type")
    void putType(String name);

    @GET
    int getId(int value);

    @PUT
    void setId(int id);
}
```

- 读取方法必须指定同类型的默认值参数 ，如果无则爆出异常。
- 应该尽量使用 key 值来指定存储的键值，而不是根据方法名得来的默认键值。

**2.实现接口**
```
PrefsApi prefsApi = Knife.create(this, PrefsApi.class);
prefsApi.putType("Harry Potty");
String name = prefsApi.getTheme("Dark");
```


**引用**
```
maven {
    url 'https://dl.bintray.com/liuxiangtian/xiang/'
} 

compile 'org.lxt.xiang:library:1.1.0'
```

