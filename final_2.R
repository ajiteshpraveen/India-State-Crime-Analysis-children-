utilities<-read.csv("E:/Study/Data Mining Dataset/child_crime_updated_14thMarch.csv")
str(utilities)
z<-utilities[,-c(1,1)]
z1<-z[,-c(1,1)]
str(z1)
z1
distance <- dist(z1)
distance
hc.c <- hclust(distance)
plot(hc.c,labels=utilities$States)
hc.c
plot(Male ~ Female,utilities)
#with(utilities,text(Persons.arrested.during.the.year_Male ~ Persons.arrested.during.the.year_Female,labels=Crime.Head))
with(utilities,text(Male ~ Female,labels=States,pos=4,cex=.5))