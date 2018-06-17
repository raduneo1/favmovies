# favmovies

Favorite movie application

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
- setup Stomp and WebSockets on client and server side to notify users when new reviews are published to the REST API:
Home page on client side will update when this happens


Challenges:



Main features: