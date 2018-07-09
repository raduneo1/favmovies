# favmovies

Favorite movie application

Main features:
- REST: uses Data REST to expose repository as REST API for CRUD operations, using HAL/HATEOS metadata
- REACT: uses React to represent the UI and React-router to structure the client-side routing
- REAL-TIME: uses WebSocket (ReactSock) and server-side CORS config and event handlers in order to allow the server to notify all site users of new reviews in real time and update reviews in real time
- 
- TMDB: uses tMDB for movie API requests to acquire movie data and images
- PRIME REACT: uses PrimeReact for uniform styling and dynamic React-ready Components, such as movie Autocomplete, and dynamic/sortable tables
- OTHER: auditing is setup on server side in order to track when entities are created, event handler also used to prevent POST conflicts
- TESTING: 
- VALIDATION: Validation using JSR 380 (Bean Validation 2.0) on server side and PrimeReact on client side

v1.0
- show list of movies loaded from REST API (hardcoded)
- search movie from tMDB API (AJAX) and add to list of movies
- setup CORS server side to allow requests to server using request filtering
- handle POST conflicts (server side using Event Handler)
- create Movie page for complete info on movie from tMDB (different AJAX request)

v2.0
- use ReactRouter to setup routing for React components
- switch to PrimeReact for uniform UI and powerful components that are React-ready
- Movie Search/Autocomplete is now implemented using PrimeReact, and clicking one suggestion loads the movie page (chained GET requests)
- Movie Search is now more responsive (search results update as you type)
- rating star component added to movie page
- post requests now correctly setup to save only "rating" and "review" movie info, following a button 'click' event

v3.0

- rating/review info now obtained from server side upon loading of Movie page, using Spring Data query language (queryDSL)
- Movie save button now only enabled if there are changes (to 'rating' or 'review') to save
- Favorite Movies reimplemented as PrimeReact DataTable
- Favorite Movies table now comes with filtering and sorting
- Movie now correctly uses POST or PUT request depending on situation
- Show dynamic movie rating description next to movie rating (i.e. 'Excellent', 'Poor', 'Average')
- setup Stomp and WebSockets on client and server side to notify users when new reviews are published to the REST API: Home page on client side will update when this happens

v4.0

- moved Socket logic to Main in order to have access to send growl messages through entire application
- socket endpoint added for review updates (before we only had review creation)
- changed types of Movie object server-side in order to allow for larger reviews (> 250 characters due to VarChar(255) type used in database by default for String)
- setup auditing and custom query method on server side in order to record when new movies are created and sort new reviews by time of creation
- moved all constants to Const.js files and removed 'magic numbers'
loaded movie image info on startup and shown image on the Movie and Review pages (medium and small images, respectively)
- added new movie fields such as movie director and movie genre
- setup bean validation on server side using Bean Validation 2.0

v5.0

- migrated from font-awesome to primeicons (due to dependencies in new primereact)
- created User entity + relationships
- completed security login setup for Spring security
- adjusted CORS and requests on client side to include credentials, in order - - for requests to function with new security policies
- changed overall file structure to make it much more organized
- switched to use frontend-maven-plugin instead of create-react-app, which - - - allows serving the client-side at the same location (URL) as server-side (frontend-maven-plugin handles installing npp, the necessary modules, and - using webpack to serve the client side code automatically)

v6.0
- major refactor to use Webpack (sans CRA) and Maven folder structure, needed 
to make Spring security functional on index.html
- fixed entity relationships and adjusted controllers and repository methods for the addition of User entity
- increased security on User repositories

KNOWN BUG: WHen changing info of existing movie, will POST instead of PUT



Challenges:



Main features:
