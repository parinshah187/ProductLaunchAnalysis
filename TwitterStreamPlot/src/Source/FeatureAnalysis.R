scoref.classifyFeature = function(sentences, continent, f.words, .progress='none')
{
	require(plyr)
	require(stringr)
	
	# we have a vector of sentences. plyr handles a list or a vector as an "l" for us
	# we want an array of scores back, so we use "l" + "a" + "ply" = laply
	scores = laply(sentences, function(sentence, f.words) {
			
		# split into words. str_split is in the stringr package
		word.list = str_split(sentence, '\\s+')
		# sometimes a list() is one level of hierarchy too much
		words = unlist(word.list)
	
		f.matches = match(words, f.words)
		
		f.matches = !is.na(f.matches)
			
		# TRUE/FALSE is treated as 1/0 by sum():
		score = sum(f.matches)		
		
		return(score)
		

	}, f.words, .progress=.progress )

scores.df = data.frame(Tweet=sentences, Continent=continent, Score=scores)
return(scores.df)
}

