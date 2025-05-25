#1 ricordarsi di mettere l'ultima versione  esempio ad oggi cpassbatch-1.5.0.jar
#2 ricordarsi modificare i servizi locali (cpassbe) con il puntamento al db interessato e di startare i servizzi stessi
N.B.   cambiare iò puntamento sul file local della variabile url con l'indicazione del db opportuno
		cambiare il numero di versione del jar (es. cpassbatch-1.3.0.jar)


cd C:\myworkspace\CPASS\cpassbatch
"C:\Program Files\apache-maven-3.6.3\bin\mvn.cmd" clean package -P local

#NB adattare il nome cpassbatch-?.?.?.jar in base al numero di versione letta nel pom
"C:\Program Files\Java\jdk-11.0.6\bin\java.exe"  -cp target\cpassbatch-2.0.0.jar it.csi.cpass.cpassbatch.Main AGGIORNAMENTO_IMPEGNI 0 2024-10-10



1) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it (con la vostra matricola)

2) TEST beehive ssh nodes connect ts-wf01-trasv-batch-cpass.site02.nivolapiemonte.it   ---> partizione COTO test utente
2) PROD beehive ssh nodes connect ??????????????      --> prod 
                                  
                                  
3) su - skedul per componente script e batch
3) su - awf170 per componente be del batch



per vedere i log di cpassbatch spostarsi su /skedul/java/cpass/<<ente>>/cpassbatch/log
dove <<ente>> può valere:
rp-01 int-01 pvto-01 coto-01

per vedere i log di cpassbe spostarsi su /appserv/jboss/awf170/part<<progr>>cpassnode01/standalone/log
dove <<progr>> vale:
001 per rp
002 per mult
003 per cmto
004 per coto

directory dove vengono copiati e rinominati i file di impegni e subimpegni:
/skedul/progetti/cpass/dati/wrk/impegni/

area di interscambio dove vengono depositati i file di sicraweb_coto:
/interscambio_cpass/sicraweb_coto


N.B. in caso di problemi 
fare stop e start del nodo 1 e 2 su awf230 
poi su root
systemctl start wildfly230-part003cpassnode01



copio il file impegni 
1) ssh 71027@cmpto1-cons02.site01.nivolapiemonte.it
2) beehive ssh nodes-files get  wf1-trasv-batch-cpass.site01.nivolapiemonte.it /interscambio_cpass/sicraweb_coto/2024-impegno-COTO_20240108_235959.csv ./2024-impegno-COTO_20240108_235959.csv
3) con winscp navigo la macchina ponte e mi copio il file in locale





