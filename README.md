# Festo-Test

API endpoints
http:http://localhost:8080/movies
accepts querry paramter years, seperated by comma
example: http:http://localhost:8080/movies?years=1997,1998,2001
gets a list of movies, since the provided api doesn't provide movie list by year, I made a second enpoint

http:http://localhost:8080/movies/dummy-list
simulated API response to the provided one
used to show functionality of the code.
