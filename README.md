Selenide examples: Appium
=========================

## Mobile automation 

How to test mobile application with Selenide and Appium

### How to run the example

* Run the emulator:
  > open Android Studio -> "Android Virtual Device Manager" -> Run

  Alternatively, you can start emulator from command line:
  > emulator -avd Pixel_4_API_28_1

* And run the test:
   > ./gradlew android-tests

Thanks to [Maryna Kolesnik](https://github.com/mkpythonanywhereblog) for CalculatorTest example!
Her original sample can be found [here](https://gist.github.com/mkpythonanywhereblog/d1fb3dca2e66146f519f) 

NB! This project uses a simple approach for tests: it just calls Selenide standard `$` method to find elements in mobile app.
I like it. It's just good enough.

But it's also possible to use Android- and IOS- specific annotations like `@AndroidFindBy`. 
Library [selenide-appium](https://github.com/selenide/selenide/tree/main/modules/appium) has Selenide+Appium specific page factory 
for supporting those annotations and many other features. 
