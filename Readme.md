#Up42 Backend app

**To Run the app**

In the command line type:

``./gradlew bootRun``

After that the app should be available on http://localhost:8080/


**To Run the test suite**

``./gradlew bootRun``

##Discussions
- Assumptions on Data:

The FeatureStore is loading the entire file into a HashMap from a java resource. In the real life this file may come
from a distributed file system or some "Real-time" streaming service, however the source of the data can be easily
replaced inside the FeatureStore without major impact in the APP.

**Memory Size**
This approach is assumming that the data batches fit in memory, that may not scale depending on the nature of the data 
and how the JSON files are produced. In this case the entire transformed data set is about 2.3 Kilobytes with 13 records.
If this is a good sampling of the data size, within 1GB of memory we can load more than 5'000'000.00 of records (without any compression).

As most of the size of the record relies on the quicklook field, different strategies can be applied to decrease the memory
even more, for example externalizing the base64 string in another file and caching the images.

**Response Time**
The critical points of response time are the **file parsing** (which happens on first request because of the lazy mechanism)
and the **base64** to bytes conversion. 

The base64 to bytes conversion can be cached (or pre-processed) to increase response time.
The file parsing could be greatly improved by pre-parsing the JSON into a binary format.

**Scaling**
As this app is reading from a JSON file, to horizontally scale this service we might need some distributed file system
that serves the files from whatever sources they come. The files can be replicated with some hashing strategy that 
can also be used for load-balancing.
Ultimately a document based database can be used (mongodb etc), however if the in-memory constraint can be kept that would be a lot faster