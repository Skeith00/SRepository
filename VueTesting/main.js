Vue.component('task-list', {
	template: '<div><task v-for="task in tasks"> {{ task.task }}</task></div>',
	data(){
		return {
			tasks: [
				{ task: "Go to the store", completed: true},
				{ task: "Finish screencast", completed: false},
				{ task: "Clear inbox", completed: false},
				{ task: "Clean room", completed: true}
			]
		}
	}
});

Vue.component('task', {
	template: '<li><slot></slot></li>'
});

Vue.component('message', {
	props: ['title', 'body'],
	template: `
<article class="message" v-show="isVisible">
  <div class="message-header">
    <p> {{ title }} </p>
    <button class="delete" aria-label="delete" v-on:click="isVisible = false"></button>
  </div>
  <div class="message-body">
  {{ body }}
  </div>
</article>`,
	data() {
		return {
			isVisible: true
		}
	},
	methods: {
		hideModal() {
			this.isVisible = false;
		}
	}
});

Vue.component('modal', {
	template: `
				<div class="modal is-active">
					<div class="modal-background"></div>
					<div class="modal-content">
						<div class="box">
							<p>Lorem ipsum</p>
						</div>
					</div>
					<button class="modal-close is-large" aria-label="close" @click="$emit('close')"></button>
				</div>`
});

Vue.component('tabs', {
	template: `
	<div>	
		<div class="tabs">
			<ul>
				<li v-for="tab in tabs" :class="{'is-active': tab.isActive }">
					<a :href="tab.href" @click="selectTab(tab)">{{ tab.name }}</a>
				</li>
			</ul>
		</div>
		<div class="tabs-details">
			<slot></slot>
		</div>
	</div>
	`,
	data() {
		return { tabs: []}		
	},
	created() {
		this.tabs = this.$children;
	},
	methods: {
		selectTab(selectedTab) {
			this.tabs.forEach(tab => {
				tab.isActive = (tab.name == selectedTab.name);
			})
		}
	}
});

Vue.component('tab', {
	props: {
		name: {required: true},
		selected: {default: false}
	},
	template: `
	<div v-show=isActive><slot></slot></div>
	`,
	data() {
		return {isActive: false}
	},
	mounted() {
		this.isActive = this.selected;
	},	
	computed: {
		href() {
			return '#' + this.name.toLowerCase().replace(/ /g, '-');
		}
	}	
});

new Vue({
	el: '#root',
	data: {
		showModal: false
	}
});