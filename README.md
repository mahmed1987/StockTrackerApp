# Stock Tracker App 
###### A demo showcasing the industry standard approach (Multi modules , MVVM , CLEAN, Compose) for developing in android

This application takes the approach of extreme code isolation by distributing various components of the application in modules. The following modules together make the application

## Common
This module would not have any dependencies. However THIS WILL BE a dependency for the other modules in the application. As the name suggests this module will house common functionality that is required throughout the application. Most importantly this will host
###### Purposes
1. Base classes of various android components ( Fragments, Activites)
2. Extension methods that would be used in the application
3. Styles
###### Dependencies
None

## App
This module will be the one which would be the entry point of our application. This module will be responsible for creating our DI container and also host the main build gradle file as well as the android manifest. This module will be dependent on all the other modules in the application. This is necessary for it to create the DI Container graph.
Other than creating the dependency graph , this module has no other purpose than to launch the landing page of the application (which would reside in another module)
###### Purposes
1. Create the DI container
2. Launch the landing page of the application
###### Dependencies
* implementation project(":common")
* implementation project(":repositories")
* implementation project(":network")
* implementation project(":stocksListFeature")
* implementation project(":business")

## Business
This module will hold all the non UI code of our application. In clean architecture way of speaking , this would hold the usecases for our application.
This module will not be aware of the UI which would use the business it exposes. This module will have dependencies over the repository module of our application
###### Purposes
1. Provide usecases for our application (used by the UI )
###### Dependencies
* implementation project(":repositories")
* implementation project(":common")
* implementation project(":dtos")

## Repository
This module will hold the repositories for all the data entities we are to work with in our application.
###### Purpose
1. Provide data to the business module
###### Dependencies
* implementation project(":dtos")
* implementation project(":network")
* implementation project(":common")

## Dtos
This module is the glue which binds the UI with the data it needs to display. The UI is never aware of anything other than the business module. However the challenge then would be to send a piece of data upstream from repositories to business to UI. Dtos is a module on which the UI , business and repositories are together dependent , without being aware of each other directly. This gives us isolation of the UI with the rest of the system
###### Purpose
1. Provide the DTOs to the UI (which would be translated version of the actual data coming from Room/Web)
###### Dependencies
None

## Network
This module exists at the lowest level of the application module graph. By that I mean , this module has no dependencies and only the **Repository** module is the only module which is aware of this module
###### Purpose
1. Provide the network related functionality to the application (Retrofit lives in this module)
###### Dependencies
* implementation project(":common")
* implementation project(":dtos")
## Stock List Feature (UI module)
This would be the module which would host the stock list feature of the application. This feature includes two composables and takes its data from the business module. Other than business module , this UI would be completely agnostic of the rest of the system
###### Purpose
1. Provide a list of stocks.
2. Provides detail of a single stock (Repeats every 8 seconds).
###### Dependencies
* implementation project(":business")
* implementation project(":common")
* implementation project(":dtos")
## Test cases
The only test cases in the application as of now are relating to the BaseViewModel
1. BaseViewModelTest - Repository Test




![image](https://github.com/mahmed1987/StockTrackerApp/assets/26518062/b6fe642e-3f39-47a8-b0dc-3d619da025d1)
![image](https://github.com/mahmed1987/StockTrackerApp/assets/26518062/8d0d43d9-9f4f-4a52-a9a7-691dc4b29c3b)
![image](https://github.com/mahmed1987/StockTrackerApp/assets/26518062/d6998fa8-87d9-440c-bae7-f638a40bd7dd)
![image](https://github.com/mahmed1987/StockTrackerApp/assets/26518062/9acbe87f-a990-4c71-bf77-ce3256d2b027)




