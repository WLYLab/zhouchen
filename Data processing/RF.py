# encoding:utf-8
import numpy as np
import pandas as pd
from sklearn import metrics
from sklearn.ensemble import RandomForestClassifier
import sys
import math
import easy_excel
from sklearn.model_selection import *
from sklearn.metrics import roc_curve, auc
from sklearn.model_selection import GridSearchCV
from sklearn.model_selection import cross_val_predict

path=""
inputname=sys.argv[1]
outputname=inputname.split('.')[0]
crossvalidation_value=int(sys.argv[2])
CPU_values=int (sys.argv[3])

def performance(labelArr, predictArr):
    #labelArr[i] is actual value,predictArr[i] is predict value
    TP = 0.; TN = 0.; FP = 0.; FN = 0.
    for i in range(len(labelArr)):
        if labelArr[i] == 1 and predictArr[i] == 1:
            TP += 1.
        if labelArr[i] == 1 and predictArr[i] == 0:
            FN += 1.
        if labelArr[i] == 0 and predictArr[i] == 1:
            FP += 1.
        if labelArr[i] == 0 and predictArr[i] == 0:
            TN += 1.
    SN = TP/(TP + FN) #Sensitivity = TP/P  and P = TP + FN
    SP = TN/(FP + TN) #Specificity = TN/N  and N = TN + FP
    precision=TP/(TP+FP)
    recall=TP/(TP+FN)
    GM=math.sqrt(recall*SP)
    #MCC = (TP*TN-FP*FN)/math.sqrt((TP+FP)*(TP+FN)*(TN+FP)*(TN+FN))
    return precision,recall,SN,SP,GM,TP,TN,FP,FN



if __name__=="__main__":

	datapath=path+outputname+'.csv'
	classifier="RF"
	mode="crossvalidation"
	print ("start")

	train_data=pd.read_csv(datapath,header=None,index_col=None)
	X=np.array(train_data)
	Y=list(map(lambda x:1, range(len(train_data)//2)))
	Y2=list(map(lambda x:0, range(len(train_data)//2)))
	Y.extend(Y2)
	Y=np.array(Y)

	rf=RandomForestClassifier(max_features='sqrt' ,random_state=10)
	parameters={'n_estimators':range(10,100,10),'max_depth':range(3,14,2),'min_samples_split':range(50,201,20),'min_samples_leaf':range(10,60,10),}
	clf=GridSearchCV(rf,parameters,cv=crossvalidation_value,n_jobs=CPU_values,scoring='accuracy')
	clf.fit(X,Y)
	n_estimators=clf.best_params_['n_estimators']
	print("n_estimators:",n_estimators)
	max_depth=clf.best_params_['max_depth']
	print("max_depth:",max_depth)
	min_samples_split=clf.best_params_['min_samples_split']
	print("min_samples_split:",min_samples_split)
	min_samples_leaf=clf.best_params_['min_samples_leaf']
	print("min_samples_leaf:",min_samples_leaf)
	'''n_estimators=30
	max_depth=5
	min_samples_leaf=10
	min_samples_split=70'''
	rf0=RandomForestClassifier(n_estimators=n_estimators,max_depth=max_depth,min_samples_leaf=min_samples_leaf,min_samples_split=min_samples_split,max_features='sqrt' ,random_state=10)
	y_predict=cross_val_predict(rf0,X,Y,cv=crossvalidation_value,n_jobs=CPU_values)
	y_predict_prob=cross_val_predict(rf0,X,Y,cv=crossvalidation_value,n_jobs=CPU_values,method='predict_proba')
	predict_save=[Y.astype(int),y_predict.astype(int),y_predict_prob[:,1]]
	print(predict_save)
	predict_save=np.array(predict_save).T
	print(predict_save)
	pd.DataFrame(predict_save).to_csv('Before_'+path+classifier+mode+outputname+"_"+'_predict_crossValidation.csv',header=None,index=False)
	ROC_AUC_area=metrics.roc_auc_score(Y,y_predict_prob[:,1])
	ACC=metrics.accuracy_score(Y,y_predict)
	precision,recall,SN,SP,GM,TP,TN,FP,FN=performance(Y,y_predict)
	F1_Score=metrics.f1_score(Y, y_predict)
	F_measure=F1_Score
	MCC=metrics.matthews_corrcoef(Y, y_predict)
	pos=TP+FN
	neg=FP+TN
	savedata=[[['RF',ACC,precision,recall,SN,SP,GM,F_measure,F1_Score,MCC,ROC_AUC_area,TP,FN,FP,TN,pos,neg]]]
	easy_excel.save(classifier+"_crossvalidation",[str(X.shape[1])],savedata,path+'cross_validation_'+classifier+"_"+outputname+'.xls')






	 
	  
