projectDir = getwd()
print(projectDir)
VERBOSE=TRUE

if (VERBOSE)
	print('Loading libraries and functions for project')

library(plyr)

# load our score.classify() function

source('E:/Workspace/TwitterStreamPlot/src/Source/classification.R')
source('E:/Workspace/TwitterStreamPlot/src/Source/FeatureAnalysis.R')

print('Reading CSV file and doing vector conversion')
data <- read.csv('E:/Workspace/TwitterStreamPlot/src/Source/CSVResult.csv')
print('File read.')
data1 = as.vector(as.matrix(data$Tweet))

features = scan('E:/Workspace/TwitterStreamPlot/src/Source/Features.txt', what='character', comment.char='#')
f.words = c(features)	

print('Reading file containing positive keywords')
pos = scan('E:/Workspace/TwitterStreamPlot/src/Source/Positive.txt', what='character', comment.char='#')
pos.words = c(pos, 'upgrade', 'wait', 'waiting')

print('Reading file containing negative keywords')
neg = scan('E:/Workspace/TwitterStreamPlot/src/Source/Negative.txt', what='character', comment.char=';')
neg.words = c(neg, 'wtf', 'epicfail')

print('Calculating scores for tweets')
result = score.classify(data1, data$Continent, pos.words, neg.words)

print('Feature Analysis')
FeatureResult = scoref.classifyFeature(data1, data$Continent, f.words)

write.csv(result, file='E:/Workspace/TwitterStreamPlot/src/Source/result.csv')
write.csv(FeatureResult, file='E:/Workspace/TwitterStreamPlot/src/Source/FeatureResult.csv')

print('Done')
print('Results stored in result.csv in current working directory')