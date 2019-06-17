Selenide examples: Appium
=========================

## Mobile automation 

How to test mobile application with Selenide and Appium

### How to run the example

* Run the emulator:
  > open Android Stuiod -> "Android Virtual Device Manager" -> Run

* Run appium server:
   > appium

* And finally, run the test:
   > gradle test

Thanks to [Maryna Kolesnik](https://github.com/mkpythonanywhereblog) for CalculatorTest example!
Her original sample can be found [here](https://gist.github.com/mkpythonanywhereblog/d1fb3dca2e66146f519f) 

NB! This project uses a simple approach for tests: it just calls Selenide standard `$` method to find elements in mobile app.
I like it. It's just good enough.

But if you want to use Android- and IOS- specific annotations like `@AndroidFindBy`, consider using library [selenide-appium](https://github.com/selenide/selenide-appium). It has Selenide+Appium specific page factory for supporting those annotations. 
