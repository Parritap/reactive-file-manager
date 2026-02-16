docker run \
  --name file-manager-db \
  -e POSTGRES_PASSWORD=root \
  -p 5432:5432 \
  -d postgres