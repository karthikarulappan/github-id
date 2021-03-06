# **github-id**

**github-id** is a spring boot API that accepts an valid github user name or id and returns returns Follower GitHub ID’s (up to 5 Followers total) associated with the passed in information. It will retrieve data up to 3 levels deep, repeating the process of retrieving Followers for each follower found.

**App Highlights**

This section highlights the components/libraries used to develop this application

* Spring Boot v2.1.13
* Spring webflux
* Project Reactor
* Java v1.8
* Swagger Documentation

**Testing this application**

This application has been hosted in pivotal cloud foundry (paas) and can be tested [here](https://github-id-app.cfapps.io/swagger-ui.html)

**Steps to test in swagger link**

* Once the swagger page loaded after clicking the link above, scroll down to the "github-id-controller" section and click on it to expand
* Click "Try it out" button on the right corner
* Provide a valid github user name in the field "uname" and hit "Execute" button
* Scroll further down the page to see the response from the server

