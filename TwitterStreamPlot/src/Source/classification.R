score.classify = function(sentences, continent, pos.words, neg.words, .progress='none')
{
	require(plyr)
	require(stringr)
	
	# we have a vector of sentences. plyr handles a list or a vector as an "l" for us
	# we want an array of scores back, so we use "l" + "a" + "ply" = laply
	scores = laply(sentences, function(sentence, pos.words, neg.words) {
			
	# split into words. str_split is in the stringr package
	word.list = str_split(sentence, '\\s+')
	# sometimes a list() is one level of hierarchy too much
	words = unlist(word.list)

	# compare our words to positive & negative terms
	pos.matches = match(words, pos.words)
	neg.matches = match(words, neg.words)
	
	# match() returns the position of the matched term or NA
	pos.matches = !is.na(pos.matches)
	neg.matches = !is.na(neg.matches)

	# TRUE/FALSE is treated as 1/0 by sum():
	score = sum(pos.matches) - sum(neg.matches)

	return(score)
}, pos.words, neg.words, .progress=.progress )

scores.df = data.frame(Score=scores, Tweet=sentences, Continent=continent)
return(scores.df)
}
