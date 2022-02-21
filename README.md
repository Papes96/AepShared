# AepShared
Sales Management app for personal commerce "Aventuras en Pañales"

The project goal is to build an App and Server to replace the current commerce app made in [AppSheet](https://www.appsheet.com/).

The project is divided in two *App* and *Server*.

## App [(APK)](https://drive.google.com/file/d/1oCtfcOuvcfKkDsOXOFFJgxW4k84zLsDj/view?usp=sharing)
Build in Kotlin using [Android Studio Bumblebee | 2021.1.1 Patch 1](https://androidstudio.googleblog.com/2022/02/android-studio-bumblebee-202111-patch-1.html), the app goal is mimic most of the features in the commerce AppSheet app.

The app should be able to:
* Retrieve the inventory list from the server. ✔️
* Retrieve the sales list from the server. ✔️
* Save both list in a room db for efficency. ✔️
* Search and display the inventory list. ✔️
* Search and display the sales list. ✔️ 
* Scan barcodes for inventory search. ✔️
* Create new sales with the server. ❌
* Notify the admin about new sales. ❌
* Edit inventory from the server. ❌
* Edit sales from the server. ❌

## Server
Build in Kotlin using [Ktor](https://ktor.io/) and deployed in GCP. The server uses the commerce [sheet (shared copy)](https://docs.google.com/spreadsheets/d/1N_qHFkxU2AFdAYJYWvk23agF1rcqaZsq7qAyV71dPvY/edit?usp=sharing) as source of truth.

GCP is also used to run the [Sheets API](https://developers.google.com/sheets/api) within the server.

Utils:
* [Deployed server URL](https://aepshared.rj.r.appspot.com/)
* [Postman Collection](https://drive.google.com/file/d/1kmW8DKgB8gHXujhh-uTmkDUKwCcucFLv/view?usp=sharing)

The app should be able to:
* Retrieve the inventory list from the spreadsheet. ✔️
* Retrieve a product detail from the spreadsheet. ✔️
* Retrieve the sales list from the speadsheet. ✔️
* Retrieve the promotions list from the speadsheet. ✔️
* Calculate the sale total cost taking the promotions into account. ✔️
* Persist new sales in the spreadsheet. ✔️
* Upload the project to GCloud. ✔️
* Edit inventory in the speadsheet. ❌
* Edit sales in the server. ❌

