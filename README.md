# fontcustomizer
Android module for easy using a custom fonts in the project

### How to use:
* Project -> New -> Module -> Import Gradle Project
* put [font-file].ttf into app/src/main/assets/fonts
* call this for each .ttf in onCreate() method of Application or Activity or Fragment:
```java
        FontCache.getInstance(context).addFont("any name here", "[font-file].ttf");
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

