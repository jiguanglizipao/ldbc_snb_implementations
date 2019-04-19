__dbf_set ('txn_after_image_limit', 10000000000);
__dbf_set ('enable_mt_txn', 1);
__dbf_set ('enable_mt_transact', 1);

delete from load_list;
ld_dir ('/home/jiguanglizipao/ldbc_snb_datagen/SF10_ttl/social_network', '%.ttl', 'sib');

rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 
rdf_loader_run () & 

wait;
wait_for_children;

checkpoint;
