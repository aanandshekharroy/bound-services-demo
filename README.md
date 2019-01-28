# bound-services-demo
A sample app to demonstrate usage of Bound services in Android.
**Services** is the Android component which is used to perform long-running background tasks. There are other Android components which run in the background too, like Broadcast receiver and JobScheduler, but they are not used for long running tasks. Matter of fact, a broadcast receiver, though runs in the background, need to exit the `onReceive` method within 10 seconds or else Android will kill it. JobScheduler, also runs in the background, is used for batching the background tasks. 

So generally speaking, Services are the components you should go to if you want to keep something running in the background 
well beyond the lifecycle of the activity.
Now there are some cases where you would want to run the services for as long as other components want. 
Let's say you are using a service which generates a random number every few seconds and your activity displays that number. In this case, running a service beyond the activity's life doesn't make sense (as there is no one else to consume the random number). 

...Read more about bound services at this [link](http://www.aanandshekharroy.com/articles/2019-01/bound-services-in-android)
