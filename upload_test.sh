read -p "Enter file path: " FILE_PATH
read -p "Enter title: " TITLE
read -p "Enter description: " DESC
read -p "Enter subject: " SUBJ

META_JSON="{\"title\":\"$TITLE\",\"description\":\"$DESC\",\"subject\":\"$SUBJ\"}"

API_URL="http://localhost:8080/api/notes/upload"

curl -X POST "$API_URL" \
  -F "file=@${FILE_PATH}" \
  -F "meta=${META_JSON};type=application/json"
