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
* put your [font-file].ttf files into app/src/main/assets/fonts;
* in onCreate() method (of Application or Activity or Fragment) call this for caching all names of the fonts located in the assets/fonts:
```java
        FontCache.getInstance(getApplicationContext());
``` 
  

  or call this for each .ttf for caching a custom names of the fonts:
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

