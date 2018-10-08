echo
echo =====================
echo DEV ENV SCRIPT pointed to DEV
echo =====================
echo

PROJECT_ROOT=/home/endlos/billing/billing
cd $PROJECT_ROOT

sudo git remote update

LOCAL=$(git rev-parse @)
REMOTE=$(git rev-parse @{u})

if [ $LOCAL = $REMOTE ];
then
        echo "No any new commits are found on remote branch"
elif [ $LOCAL != $REMOTE ];
then
        echo "Required to pull changes"
        sudo git reset --hard origin/dev
        sudo git pull

        sudo killall -9 java || true
        echo "Changes are deployed. Available for review"
        sudo -E nohup mvn spring-boot:run &
else
        echo "Nothing to do"
fi


