# Synpulse Front End Challenge

<img src="https://github.com/CumiTerbang/SynpulseFrontEndChallenge/blob/master/readme_assets/screenshot_1.jpg" width="200" height="355,56"> <img src="https://github.com/CumiTerbang/SynpulseFrontEndChallenge/blob/master/readme_assets/screenshot_2.jpg" width="200" height="355,56">

my approach for [SynpulseTechnology's Qualification Test Frontend Developer](https://gist.github.com/SynpulseTechnology/37f4296cb19e09133936aac5480d9bf0)
 by making an android application based on the requirement as the solution

Click [here](https://drive.google.com/file/d/1QiGAvMRGAHhUvlov-SMTxuGIHOI6AJk3/view?usp=sharing) to download the app


## Features
1. Search for financial instruments (stocks, etfs, commodities, crypto) on the NYSE/Nasdaq stock exchanges
2. learn more information about the stocks

## Supported Device
- Android device with minimum API 16 **(Jelly Bean)**

## Build App requirements
- Recomended using Android Studio 4.1.2
- Using Kotlin 1.3.72

## Instructions
1. Clone from this repository
    - Copy repository url
    - Open your Android Studio
    - New -> Project from Version Control..
    - Paste the url, click OK
2. Replace demo api key with your [Alpha Vantage](https://www.alphavantage.co/) api key [here](https://github.com/CumiTerbang/SynpulseFrontEndChallenge/blob/master/app/src/main/java/com/haryop/synpulsefrontendchallenge/utils/ConstantsObj.kt)
    - utils > ConstantsObj > ALPHAVANTAGE_APIKEY
3. Prepare the Android Virtual Device or real device
4. Build and deploy the app module

## Code Design & Structure
This project is using MVVM design pattern. The project directory consist of 3 directories:
1. **data**: The M (Model) in MVVM. Where we perform data operations.
2. **ui**: Fragments and ViewModels helping to display data to the user.
3. **util**: Urilities directory for helper classes and functions.
