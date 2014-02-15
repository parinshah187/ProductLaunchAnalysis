projectDir = getwd()
#print(projectDir)
VERBOSE=TRUE

library(plyr)

# load our score.classify() function

source('E:/Workspace/TwitterStreamPlot/src/Source/classification.R')

print('Reading CSV file and doing vector conversion')
data <- read.csv('E:/Workspace/TwitterStreamPlot/src/Source/FResult.csv')
print('File read.')
data1 = as.vector(as.matrix(data$Tweet))

print('Reading file containing positive keywords')
pos = scan('E:/Workspace/TwitterStreamPlot/src/Source/Positive.txt', what='character', comment.char=';x')
pos.words = c(pos, 'upgrade', 'wait', 'waiting')

print('Reading file containing negative keywords')
neg = scan('E:/Workspace/TwitterStreamPlot/src/Source/Negative.txt', what='character', comment.char=';')
neg.words = c(neg, 'wtf', 'epicfail')

print('Calculating scores for tweets')
result = score.classify(data1, data$Continent, pos.words, neg.words)

write.csv(result, file='E:/Workspace/TwitterStreamPlot/src/Source/Features.csv')

print('Done')
print('Results stored in Features.csv in current working directory')
