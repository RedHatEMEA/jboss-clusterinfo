#!/bin/bash

LTS=target/logs/loadtest_summary.csv
LTSD=target/logs/loadtest_summary_data-$1.log

if [ ! -f $LTS ]; then
	echo "Concurrent Requests,Number of Requests,Requests Per Second,Mean Millisecond Response Time,Number of Errors" > $LTS
fi

mv target/logs/out* $LTSD

echo -n $1, >> $LTS
grep Totals $LTSD | tr -s ' ' | cut -d ' ' -f 2 | tr -d '\n' >> $LTS
echo -n , >> $LTS

grep Totals $LTSD | tr -s ' ' | cut -d ' ' -f 6 | xargs echo "$1 * " | bc -l | tr -d '\n' >> $LTS
echo -n , >> $LTS

grep Totals $LTSD | tr -s ' ' | cut -d ' ' -f 4 | tr -d '\n' >> $LTS
echo -n , >> $LTS
grep Totals $LTSD | tr -s ' ' | cut -d ' ' -f 9 | tr -d '\n' >> $LTS
echo "" >> $LTS

echo ""
echo "Load Test Summary:"
echo ""
cat $LTS
echo ""
