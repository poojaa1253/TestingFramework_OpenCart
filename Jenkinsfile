pipeline 
{
    agent any
    
    stages {
         
          stage('Build') 
        {
            steps
            {
                 echo ("build the project")
            }
           
        }
        
        stage('Run Unit test') 
        {
            steps
            {
                 echo ("Run UTs")
            }
           
        }
        
         stage('Run Integration test') 
        {
            steps
            {
                 echo ("Run ITs")
            }
           
        }
        
          stage("Deploy to dev")
         {
            steps
            {
                echo("deploy to dev done")
            }
        }
        
        stage("Deploy to QA")
        {
            steps
            {
                echo("deploy to qa done")
            }
        }
        
        
        stage('Run regression Test on QA') 
        {
            steps{
                     echo("run test in QA")
                    
                }
            
        }
        
        stage("Deploy to Stage")
        {
            steps
            {
                echo("deploy to Stage")
            }
        }
        
        stage('Run sanity Test on Stage') 
        {
            steps
            {
                 echo("run sanity test in stage")
            }
            
        }
        
        stage("Deploy to UAT")
        {
            steps
            {
                echo("deploy to UAT")
            }
        }
        
        stage('Run sanity Test on UAT') 
        {
            steps
            {
                 echo("run sanity test in UAT")
            }
            
        }
        
        stage("Deploy to PROD")
        {
            steps
            {
                echo("deploy to PROD")
            }
        }
        
        
    }
}