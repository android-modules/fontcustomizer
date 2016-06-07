# fontcustomizer
Android module for easy using a custom fonts in the project

### How to use:
* Project -> New -> Module -> Import Gradle Project
* add dependency to build.gradle
```
dependencies {

....

compile project(':fontcustomizer');
}
```
* put [font-file].ttf into app/src/main/assets/fonts
* call this for each .ttf in onCreate() method of Application or Activity or Fragment:
```java
        FontCache.getInstance(getApplicationContext()).addFont("any-name", "[font-file].ttf");
```

* apply font:
```java
textView.setTypeface(
    FontCache.getInstance(getApplicationContext()).get("any-name")
);
```

Example:
```java
public class AppController extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();

        //Add custom font to HashMap
        FontCache.getInstance(context).addFont("regular", "SourceSansPro-Regular.ttf");
        FontCache.getInstance(context).addFont("semibold", "SourceSansPro-Semibold.ttf");
        FontCache.getInstance(context).addFont("light", "SourceSansPro-Light.ttf");
    }
    
}  
```

